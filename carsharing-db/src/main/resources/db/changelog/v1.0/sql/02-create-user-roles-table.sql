ALTER TABLE carsharing_service.db_user_roles
    ADD CONSTRAINT fk_user_roles_user
        FOREIGN KEY (db_user_id)
            REFERENCES carsharing_service.db_user (id)
            ON DELETE CASCADE;
