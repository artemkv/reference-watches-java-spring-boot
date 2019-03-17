package net.artemkv.referencewatches;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.artemkv.referencewatches.configuration.WatchesApiConfiguration;
import net.artemkv.referencewatches.controller.WatchController;
import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.dto.WatchToPostDto;
import net.artemkv.referencewatches.dto.WatchToPutDto;
import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.service.WatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(WatchController.class)
public class WatchControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WatchService service;

    @MockBean
    WatchesApiConfiguration apiConfiguration;

    @Test
    public void testGetWatch() throws Exception {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        Mockito.when(service.getWatch(watch.getId()))
            .thenReturn(watch);

        // when
        mvc.perform(get("/api/watches/345")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is((int)watch.getId())));
    }

    @Test
    public void testGetWatchNotFound() throws Exception {
        // given
        Mockito.when(service.getWatch(345))
            .thenReturn(null);

        // when
        mvc.perform(get("/api/watches/345")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testGetWatchesByTitle() throws Exception {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);

        Mockito.when(apiConfiguration.getPageSizeLimit())
            .thenReturn(100);

        List<Watch> watches = new ArrayList<>();
        watches.add(watch);
        Mockito.when(service.getWatches(
            org.mockito.ArgumentMatchers.eq("sea"),
            org.mockito.ArgumentMatchers.eq(Gender.MENS),
            org.mockito.ArgumentMatchers.eq(""),
            org.mockito.ArgumentMatchers.any(Pageable.class)))
            .thenReturn(new PageImpl<>(watches));

        // when
        mvc.perform(get("/api/watches?title=sea")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.results", hasSize(1)))
            .andExpect(jsonPath("$.results[0].id", is((int)watch.getId())));
    }

    @Test
    public void testGetWatchesByGender() throws Exception {
        // given
        Watch watch = TestData.getWatchRoyalOak(456L, 567L, 678L);

        Mockito.when(apiConfiguration.getPageSizeLimit())
            .thenReturn(100);

        List<Watch> watches = new ArrayList<>();
        watches.add(watch);
        Mockito.when(service.getWatches(
            org.mockito.ArgumentMatchers.eq(""),
            org.mockito.ArgumentMatchers.eq(Gender.LADIES),
            org.mockito.ArgumentMatchers.eq(""),
            org.mockito.ArgumentMatchers.any(Pageable.class)))
            .thenReturn(new PageImpl<>(watches));

        // when
        mvc.perform(get("/api/watches?gender=LADIES")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.results", hasSize(1)))
            .andExpect(jsonPath("$.results[0].id", is((int)watch.getId())));
    }

    @Test
    public void testPostWatch() throws Exception {
        // given
        Watch watch = TestData.getWatchSeaDweller(123L, 234L, 345L);
        WatchToPostDto watchIn = TestData.getWatchToPostDtoExplorer();
        Mockito.when(service.createWatch(org.mockito.ArgumentMatchers.any(Watch.class)))
            .thenReturn(watch);

        // when
        mvc.perform(post("/api/watches")
            // then
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(watchIn)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is((int)watch.getId())));
    }

    @Test
    public void testPutWatch() throws Exception {
        // given
        WatchToPutDto watchIn = TestData.getWatchToPutDtoExplorer();
        Mockito.when(service.updateWatch(org.mockito.ArgumentMatchers.any(Watch.class)))
            .thenReturn(true);

        // when
        mvc.perform(put("/api/watches/123")
            // then
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(watchIn)))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testPutWatchNotFound() throws Exception {
        // given
        WatchToPutDto watchIn = TestData.getWatchToPutDtoExplorer();
        Mockito.when(service.updateWatch(org.mockito.ArgumentMatchers.any(Watch.class)))
            .thenReturn(false);

        // when
        mvc.perform(put("/api/watches/123")
            // then
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(watchIn)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testPutWatchIdDoesNotMatch() throws Exception {
        // given
        WatchToPutDto watchIn = TestData.getWatchToPutDtoExplorer();

        // when
        mvc.perform(put("/api/watches/222")
            // then
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(watchIn)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutWatchEmptyId() throws Exception {
        // given
        WatchToPutDto watchIn = TestData.getWatchToPutDtoExplorer();
        watchIn.setId(0);

        // when
        mvc.perform(put("/api/watches/0")
            // then
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(watchIn)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteWatch() throws Exception {
        // given
        Mockito.when(service.deleteWatch(123))
            .thenReturn(true);

        // when
        mvc.perform(delete("/api/watches/123")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteWatchNotFound() throws Exception {
        // given
        Mockito.when(service.deleteWatch(123))
            .thenReturn(false);

        // when
        mvc.perform(delete("/api/watches/123")
            // then
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
