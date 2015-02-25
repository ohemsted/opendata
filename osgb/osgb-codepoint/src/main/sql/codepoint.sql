SET search_path = reference;

DROP TABLE codepoint;
DROP TABLE codepoint_code;

CREATE TABLE codepoint_code (
    id      SERIAL,
    code    CHAR(9),
    name    TEXT,
    PRIMARY KEY (code)
);
CREATE UNIQUE INDEX codepoint_code_i ON codepoint_code(id);
CREATE INDEX codepoint_code_n ON codepoint_code(name);

CREATE TABLE codepoint (
    id      SERIAL,
    postcode    CHAR(7) NOT NULL,
    pqi         SMALLINT,
    eastings    INTEGER NOT NULL,
    northings   INTEGER NOT NULL,
    country     INTEGER NOT NULL REFERENCES codepoint_code(id),
    county      INTEGER NOT NULL REFERENCES codepoint_code(id),
    district    INTEGER NOT NULL REFERENCES codepoint_code(id),
    ward        INTEGER NOT NULL REFERENCES codepoint_code(id),
    nhsRegion   INTEGER NOT NULL REFERENCES codepoint_code(id),
    nhsHA       INTEGER NOT NULL REFERENCES codepoint_code(id),
    primary key(postcode)
);

CREATE INDEX codepoint_en ON codepoint(eastings,northings);
