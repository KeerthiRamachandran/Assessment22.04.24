CREATE SEQUENCE requisition_details_id_seq;

CREATE TABLE requisition_details (
    id INTEGER DEFAULT nextval('requisition_details_id_seq') NOT NULL,
    brn VARCHAR NOT NULL,
    allotment_id BIGINT NOT NULL,
    amount DOUBLE PRECISION
);


