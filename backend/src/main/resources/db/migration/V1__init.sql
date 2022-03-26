CREATE TABLE mortality_table(
    id SERIAL PRIMARY KEY ,
    year integer NOT NULL,
    country varchar(255) not null,
    country_short varchar(2) not null,
    female_population double precision not null,
    male_population double precision not null,
    created_at timestamp not null,
    updated_at timestamp not null
);