CREATE TABLE users(
    id bigserial PRIMARY KEY ,
    username varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    enabled boolean DEFAULT true,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP
);

CREATE TABLE news(
    id bigserial PRIMARY KEY ,
    title varchar(255) not null,
    text varchar(400) not null,
    is_publish boolean not null,
    user_id int8 not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE notifications(
    id bigserial PRIMARY KEY ,
    text varchar(400) not null,
    user_id int8 not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE votes (
  user_id int8 NOT NULL,
  news_id int8 NOT NULL,
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_news_id FOREIGN KEY (news_id) REFERENCES news (id)
);

insert into users(username, password, role, enabled)values('admin','$2a$12$fy.2mH4aZypBbkfDelst2e8r3g4NZwjiaeR.r/LBeYyUdZcwHo9fe','ROLE_ADMIN', true);
insert into users(username, password, role, enabled)values('user','$2a$12$Y4uvYKmbVn4gpE.fWSKexeBv.x/2I4n9xoPahEEa4xojKXrdG6CyC','ROLE_USER', true);
