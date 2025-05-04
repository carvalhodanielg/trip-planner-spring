ALTER TABLE transportations
    ADD COLUMN trip_id BIGINT;

ALTER TABLE transportations
    ADD CONSTRAINT fk_transportations_trip
        FOREIGN KEY (trip_id)
            REFERENCES trips(id);