CREATE TABLE roles (
                       id BIGSERIAL NOT NULL,
                       name VARCHAR(50) NOT NULL,

                       CONSTRAINT PK_role_id PRIMARY KEY(id)
);

CREATE TABLE accounts (
                       id BIGSERIAL NOT NULL,
                       role_id BIGINT NOT NULL,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(200) NOT NULL,

                       CONSTRAINT PK_account_id PRIMARY KEY(id),
                       CONSTRAINT FK_role_id FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE users (
                       id BIGSERIAL NOT NULL,
                       account_id BIGINT NOT NULL,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       phone_number VARCHAR(50) NOT NULL,
                       address varchar(200) NOT NULL,
                       postcode VARCHAR(50) NOT NULL,

                       CONSTRAINT PK_user_id PRIMARY KEY(id),
                       CONSTRAINT FK_account_id FOREIGN KEY(account_id) REFERENCES accounts(id)
);

CREATE TABLE notifications_status (
                        id BIGSERIAL NOT NULL,
                        status_type VARCHAR(100) NOT NULL,

                        CONSTRAINT PK_notifications_status_id PRIMARY KEY(id)
);

CREATE TYPE MESSAGE_TYPE as ENUM ('registration_notification', 'subscribe_notification', 'game_recipient_info_message');

CREATE TABLE user_notifications (
                       id BIGSERIAL NOT NULL,
                       user_id BIGINT NOT NULL,
                       status_id BIGINT NOT NULL,
                       date DATE NOT NULL,
                       message_type MESSAGE_TYPE NOT NULL,

                       CONSTRAINT PK_user_notification_id PRIMARY KEY(id),
                       CONSTRAINT FK_user_id FOREIGN KEY(user_id) REFERENCES users(id),
                       CONSTRAINT FK_status_id FOREIGN KEY(status_id) REFERENCES notifications_status(id)
);

CREATE TABLE events (
                                    id BIGSERIAL NOT NULL,
                                    title VARCHAR(50) NOT NULL,
                                    description VARCHAR(200),
                                    manager_id BIGINT NOT NULL,
                                    max_present_price DECIMAL,
                                    launch_date TIMESTAMP NOT NULL,

                                    CONSTRAINT PK_event_id PRIMARY KEY(id),
                                    CONSTRAINT FK_manager_id FOREIGN KEY(manager_id) REFERENCES users(id)
);

CREATE TABLE card_in_event (
                        id BIGSERIAL NOT NULL,
                        user_id BIGINT NOT NULL,
                        event_id BIGINT NOT NULL,
                        present_wishes VARCHAR(200),
                        got_present BOOLEAN NOT NULL DEFAULT FALSE,

                        CONSTRAINT PK_card_in_event_id PRIMARY KEY(id),
                        CONSTRAINT FK_user_id FOREIGN KEY(user_id) REFERENCES users(id),
                        CONSTRAINT FK_event_id FOREIGN KEY(event_id) REFERENCES events(id)
);


CREATE TABLE santa_relationships (
                               id BIGSERIAL NOT NULL,
                               santa_id BIGINT NOT NULL,
                               recipient_id BIGINT NOT NULL,
                               event_id BIGINT NOT NULL,

                               CONSTRAINT PK_santa_relationship_id PRIMARY KEY(id),
                               CONSTRAINT FK_santa_id FOREIGN KEY(santa_id) REFERENCES users(id),
                               CONSTRAINT FK_recipient_id FOREIGN KEY(recipient_id) REFERENCES users(id),
                               CONSTRAINT FK_event_id FOREIGN KEY(event_id) REFERENCES events(id)
);

CREATE TABLE refresh_tokens (
                                     id BIGSERIAL NOT NULL,
                                     account_id BIGINT NOT NULL,
                                     token VARCHAR(200) NOT NULL,
                                     expiry_date TIMESTAMPTZ NOT NULL,

                                     CONSTRAINT PK_refresh_token_id PRIMARY KEY(id),
                                     CONSTRAINT FK_account_id FOREIGN KEY(account_id) REFERENCES accounts(id)
);

