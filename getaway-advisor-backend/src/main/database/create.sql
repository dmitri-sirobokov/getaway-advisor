DROP TABLE IF EXISTS getaway.suggestions CASCADE;
DROP TABLE IF EXISTS getaway.votes CASCADE;
DROP TABLE IF EXISTS getaway.activities CASCADE;
DROP TABLE IF EXISTS getaway.activities_suggestions CASCADE;


CREATE TABLE getaway.suggestions
(
    id uuid NOT NULL,
    name character varying(255),
    indoor boolean,
    outdoor boolean,
    active_rate smallint,
    location varchar(255),
    price decimal(6,2),
    comments text,
    PRIMARY KEY (id)
);

CREATE TABLE getaway.votes
(
    suggestion_id uuid NOT NULL,
    email varchar(255),
    yes_no boolean,
    comments text,
    PRIMARY KEY (suggestion_id, email),
    CONSTRAINT FK_VOTE_SUGGESTION FOREIGN KEY (suggestion_id)
        REFERENCES getaway.suggestions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE getaway.activities
(
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    date date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE getaway.activities_suggestions
(
    activity_id uuid NOT NULL,
    suggestion_id uuid NOT NULL,
    PRIMARY KEY (activity_id, suggestion_id),
    CONSTRAINT FK_ACTIVITIES_SUGGESTIONS_ACTIVITY FOREIGN KEY (activity_id)
        REFERENCES getaway.activities (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FK_ACTIVITIES_SUGGESTIONS_SUGGESTION FOREIGN KEY (suggestion_id)
        REFERENCES getaway.suggestions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
