CREATE TABLE dogs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    breed VARCHAR(255),
    supplier VARCHAR(255),
    badge_id VARCHAR(255),
    gender VARCHAR(20),
    birth_date DATE,
    date_acquired DATE,
    current_status VARCHAR(50),
    leaving_date DATE,
    leaving_reason VARCHAR(255),
    kennelling_characteristic VARCHAR(255),
    deleted BOOLEAN
);