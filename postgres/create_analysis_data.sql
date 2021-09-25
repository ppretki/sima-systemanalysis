create table analysis
(
    id                 TEXT,
    subject            TEXT,
    ts                 timestamp DEFAULT current_timestamp,
    active_invocations integer,
    duration           NUMERIC(8, 2),
    use_cpu            real,
    cpus               integer,
    PRIMARY KEY (id, subject)
);
