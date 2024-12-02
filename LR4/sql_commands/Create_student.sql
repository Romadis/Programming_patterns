CREATE TABLE IF NOT EXISTS student
(
  id SERIAL PRIMARY KEY,
  lastName VARCHAR(20) NOT NULL,
  firstName VARCHAR(20) NOT NULL,
  middleName VARCHAR(20) NULL,
  phone VARCHAR(15) NULL CHECK (phone ~ '^\+\d{1,15}$'),
  telegram VARCHAR(30) NULL CHECK (telegram ~ '^\@\w{3,30}$'),
  email VARCHAR(50) NULL CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
  git VARCHAR(50) NOT NULL CHECK (git LIKE 'https://github.com/%')
);

BEGIN;
CREATE TABLE IF NOT EXISTS student
(
    id serial NOT NULL,
    lastname character varying(20) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(20) COLLATE pg_catalog."default" NOT NULL,
    middlename character varying(20) COLLATE pg_catalog."default",
    phone character varying(15) COLLATE pg_catalog."default",
    telegram character varying(30) COLLATE pg_catalog."default",
    email character varying(50) COLLATE pg_catalog."default",
    git character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id)
);
END;