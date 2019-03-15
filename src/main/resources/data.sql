-- Create movements
INSERT INTO movement (title) VALUES ('Eco-Drive');
INSERT INTO movement (title) VALUES ('Automatic');
INSERT INTO movement (title) VALUES ('Hand Wind');
INSERT INTO movement (title) VALUES ('Quartz');

SET @Movement_EcoDrive = (SELECT Id FROM movement WHERE Title = 'Eco-Drive');
SET @Movement_Automatic = (SELECT Id FROM movement WHERE Title = 'Automatic');
SET @Movement_HandWind = (SELECT Id FROM movement WHERE Title = 'Hand Wind');
SET @Movement_Quartz = (SELECT Id FROM movement WHERE Title = 'Quartz');

-- Create brands
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Citizen', 1918, 'Citizen Watch is an electronics company primarily known for its watches, and is the core company of a Japanese global corporate group based in Tokyo. In addition to Citizen brand watches, it is the parent of American watch company Bulova, and is also known for manufacturing small electronics such as calculators.', now());
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Omega', 1903, 'Omega SA is a Swiss luxury watchmaker based in Biel/Bienne, Switzerland.', now());
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Seiko', 1881, 'Seiko Holdings Corporation is a Japanese holding company that has subsidiaries which manufactures and sells watches, clocks, electronic devices, semiconductors, jewelries, and optical products.', GETDATE());
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Rolex', 1915, 'Rolex SA is a Swiss luxury watch manufacturer based in Geneva, Switzerland.', now());
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Hamilton', 1892, 'The Hamilton Watch Company is a Swiss manufacturer of wristwatches based in Bienne, Switzerland.', now());
INSERT INTO brand (title, year_founded, description, date_created)
    VALUES ('Timex', 1854, 'Timex is an American manufacturing company founded in 1854.', now());

SET @Brand_Citizen = (SELECT Id FROM brand WHERE Title = 'Citizen');
SET @Brand_Omega = (SELECT Id FROM brand WHERE Title = 'Omega');
SET @Brand_Seiko = (SELECT Id FROM brand WHERE Title = 'Seiko');
SET @Brand_Rolex = (SELECT Id FROM brand WHERE Title = 'Rolex');
SET @Brand_Hamilton = (SELECT Id FROM brand WHERE Title = 'Hamilton');
SET @Brand_Timex = (SELECT Id FROM brand WHERE Title = 'Timex');

-- Genders
SET @Unisex = 0;
SET @Mens = 1;
SET @Ladies = 2;

-- Case Materials
SET @StainlessSteel = 0;
SET @Bronze = 1;
SET @Titanium = 2;
SET @Gold = 3;
SET @Brass = 4;

-- Create watches
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('AW2020-82L', 'Titanium Eco-Drive', @Mens, 41, @Titanium, now(), @Brand_Citizen, @Movement_EcoDrive);
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('SNZG15', '5 Sport Automatic', @Mens, 41, @StainlessSteel, now(), @Brand_Seiko, @Movement_Automatic);
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('114060', 'Submariner', @Mens, 40, @StainlessSteel, now(), @Brand_Rolex, @Movement_Automatic);
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('214270', 'Explorer', @Mens, 39, @StainlessSteel, now(), @Brand_Rolex, @Movement_Automatic);
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('H68201943', 'Khaki Field Blue Dial', @Mens, 38, @StainlessSteel, now(), @Brand_Hamilton, @Movement_Quartz);
INSERT INTO watch(model, title, gender, case_size, case_material, date_created, brand_id, movement_id)
    VALUES ('TW2R47500D7PF', 'Allied Chronograph', @Mens, 42, @Brass, now(), @Brand_Timex, @Movement_Quartz);