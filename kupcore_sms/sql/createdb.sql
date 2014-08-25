
DROP TABLE IF EXISTS serverports, streams, streamservers;
CREATE TABLE streamservers (
	id              BIGINT NOT NULL UNIQUE,
	type            VARCHAR(32) NOT NULL,
	ip              VARCHAR(255) NOT NULL,
    last_updated    DATETIME NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE streams (
	id              BIGINT NOT NULL AUTO_INCREMENT,
	device_id       BIGINT NOT NULL,
	channel_id      INTEGER NOT NULL,
	stream_name     VARCHAR(255) NOT NULL,
	source          VARCHAR(255) NOT NULL,
	source_type     VARCHAR(32) NOT NULL,
	output_url      VARCHAR(255),
	output_type     VARCHAR(32),
	server_id       BIGINT,
	last_updated    DATETIME NOT NULL,
	FOREIGN KEY(server_id) REFERENCES streamservers(id),
	PRIMARY KEY(id)
);

CREATE TABLE serverports (
	server_id   BIGINT NOT NULL,
	port        INT NOT NULL,
	protocol    VARCHAR(32) NOT NULL,
	FOREIGN KEY(server_id) REFERENCES streamservers(id)
);

