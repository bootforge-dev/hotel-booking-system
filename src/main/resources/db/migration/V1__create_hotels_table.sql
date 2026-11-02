CREATE TABLE hotels(
    id BIGINT NOT NULL  AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(1000),
    location VARCHAR(255) NOT NULL ,
    city VARCHAR(100) NOT NULL ,
    state VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    rating DECIMAL(2,1),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    version BIGINT NOT NULL DEFAULT 0,

    PRIMARY KEY (id),

    INDEX idx_hotels_city (city),
    INDEX idx_hotels_location (location)
);