CREATE TABLE IF NOT EXISTS road (
 id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 road_type varchar(100) NOT NULL,
 road_name int[] NOT NULL,
 width int,
 cover varchar(100),
 state varchar(100),
 description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS country (
 id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 country_code int NOT NULL,
 country_acronyme varchar(100) NOT NULL,
 country_name varchar(100) NOT NULL,
 country_flag varchar(100),
 country_population int,
 country_men_population int,
 country_women_population int,
 country_children_population int,
 country_main_crop varchar(100),
);

INSERT INTO location.country (country_code, country_acronyme, 
                              country_name, country_flag, 
                              country_population, country_men_population, 
                              country_women_population, country_children_population,
                              country_main_crop)
VALUES
  (228, 'TG', "TOGO", "", 3388, 1968, 1968, 1968,'Riz'),
  (225, 'CI', "COTE D'IVOIRE", "", 3388, 1968, 1968, 1968,'Bannane');
  

CREATE TABLE IF NOT EXISTS region
(
 id int NOT NULL,
 region_name varchar(100) NOT NULL,
 description varchar(100) NOT NULL,
 region_population int,
 region_men_population int,
 region_women_population int,
 region_children_population int,
 region_main_crop varchar(100),
 PRIMARY KEY (id)
);

INSERT INTO location.region (region_name, description, region_population, region_men_population, region_women_population, region_children_population, region_main_crop)
VALUES
  ('Maritime', 'Region maritime', 3967, 1357, 1388, 1968, 'Riz'),
  ('Plateau', 'Region des plateaux', 3967, 1357, 1388, 1968, 'Igname'),
  ('Centrale', 'Region centrale', 3967, 1357, 1388, 1968, 'Mil'),
  ('Kara', 'Region de la Kara', 3967, 1357, 1388, 1968, 'Mil'),
  ('Savane', 'Region des savanes', 3967, 1357, 1388, 1968, 'Sorgho');
  
  
CREATE TABLE IF NOT EXISTS commune
(
 id int NOT NULL,
 region_name varchar(100) NOT NULL,
 description varchar(100) NOT NULL,
 region_population int,
 region_men_population int,
 region_women_population int,
 region_children_population int,
 region_main_crop varchar(100),
 PRIMARY KEY (id)
);

INSERT INTO location.commune (id, commune_name, description, commune_population, 
commune_men_population, commune_women_population, commune_children_population, commune_main_crop, region_id)
VALUES
  ('Be', 'Region maritime', 3967, 1357, 1388, 1968, 'Riz', 1),
  ('Aflao', 'Region maritime', 3967, 1357, 1388, 1968, 'Mais', 1),
  ('Afagnan', 'Region maritime', 3967, 1357, 1388, 1968, 'Manioc', 1),
  ('Klikame', 'Region maritime', 3967, 1357, 1388, 1968, 'Riz', 1),
  ('Kouve', 'Region maritime', 3967, 1357, 1388, 1968, 'Mais', 1);