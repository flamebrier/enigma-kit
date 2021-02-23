--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0 (Debian 13.0-1.pgdg100+1)
-- Dumped by pg_dump version 13.0 (Debian 13.0-1.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: enigma; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.enigma (
    id bigint NOT NULL,
    complexity smallint,
    description character varying(1000),
    enigma_type character varying(255),
    is_public boolean,
    author_id bigint
);


ALTER TABLE public.enigma OWNER TO root;

--
-- Name: enigma_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.enigma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enigma_id_seq OWNER TO root;

--
-- Name: enigma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.enigma_id_seq OWNED BY public.enigma.id;


--
-- Name: enigma_kits; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.enigma_kits (
    enigma_id bigint NOT NULL,
    kits_id bigint NOT NULL
);


ALTER TABLE public.enigma_kits OWNER TO root;

--
-- Name: kit; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.kit (
    id bigint NOT NULL,
    description character varying(255),
    is_public boolean,
    rating smallint,
    user_id bigint
);


ALTER TABLE public.kit OWNER TO root;

--
-- Name: kit_enigmas; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.kit_enigmas (
    kit_id bigint NOT NULL,
    enigmas_id bigint NOT NULL
);


ALTER TABLE public.kit_enigmas OWNER TO root;

--
-- Name: kit_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.kit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kit_id_seq OWNER TO root;

--
-- Name: kit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.kit_id_seq OWNED BY public.kit.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(30)
);


ALTER TABLE public.role OWNER TO root;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO root;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: usr; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.usr (
    id bigint NOT NULL,
    password character varying(68),
    photo_link character varying(255),
    rating integer,
    username character varying(100)
);


ALTER TABLE public.usr OWNER TO root;

--
-- Name: usr_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.usr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usr_id_seq OWNER TO root;

--
-- Name: usr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.usr_id_seq OWNED BY public.usr.id;


--
-- Name: usr_roles; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.usr_roles (
    users_id bigint NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE public.usr_roles OWNER TO root;

--
-- Name: enigma id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.enigma ALTER COLUMN id SET DEFAULT nextval('public.enigma_id_seq'::regclass);


--
-- Name: kit id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.kit ALTER COLUMN id SET DEFAULT nextval('public.kit_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: usr id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usr ALTER COLUMN id SET DEFAULT nextval('public.usr_id_seq'::regclass);


--
-- Data for Name: enigma; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.enigma (id, complexity, description, enigma_type, is_public, author_id) FROM stdin;
\.


--
-- Data for Name: enigma_kits; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.enigma_kits (enigma_id, kits_id) FROM stdin;
\.


--
-- Data for Name: kit; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.kit (id, description, is_public, rating, user_id) FROM stdin;
\.


--
-- Data for Name: kit_enigmas; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.kit_enigmas (kit_id, enigmas_id) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.role (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_MODER
3	ROLE_ADMIN
\.


--
-- Data for Name: usr; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.usr (id, password, photo_link, rating, username) FROM stdin;
1	{bcrypt}$2a$10$4rKC6F6abCXj.vrjVnjuIuEnsbhBnHrChgAYto.Ut4e/XbnW.uwcq	\N	\N	admin
2	{bcrypt}$2a$10$KbzS0t3cCuUXxSTIJp2dqeAb1rpCdOTP6SYeRAJO6743Ci3oNZH1S	\N	\N	moder
\.


--
-- Data for Name: usr_roles; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.usr_roles (users_id, roles_id) FROM stdin;
1	3
2	2
\.


--
-- Name: enigma_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.enigma_id_seq', 1, false);


--
-- Name: kit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.kit_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: usr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.usr_id_seq', 2, true);


--
-- Name: enigma enigma_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.enigma
    ADD CONSTRAINT enigma_pkey PRIMARY KEY (id);


--
-- Name: kit kit_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.kit
    ADD CONSTRAINT kit_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: usr usr_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT usr_pkey PRIMARY KEY (id);


--
-- Name: usr_roles usr_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usr_roles
    ADD CONSTRAINT usr_roles_pkey PRIMARY KEY (users_id, roles_id);


--
-- Name: kit_enigmas fk4d3x3y65b4qmhubanbm4lv0gr; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.kit_enigmas
    ADD CONSTRAINT fk4d3x3y65b4qmhubanbm4lv0gr FOREIGN KEY (kit_id) REFERENCES public.kit(id);


--
-- Name: enigma fk8janr5sgaxn7q1xmdxpvemcfw; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.enigma
    ADD CONSTRAINT fk8janr5sgaxn7q1xmdxpvemcfw FOREIGN KEY (author_id) REFERENCES public.usr(id);


--
-- Name: enigma_kits fka30ktcm9ycsx0a8d1o0mjwcoa; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.enigma_kits
    ADD CONSTRAINT fka30ktcm9ycsx0a8d1o0mjwcoa FOREIGN KEY (kits_id) REFERENCES public.kit(id);


--
-- Name: usr_roles fkatlsh4dmfdkl5m7rdvhrls5if; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usr_roles
    ADD CONSTRAINT fkatlsh4dmfdkl5m7rdvhrls5if FOREIGN KEY (roles_id) REFERENCES public.role(id);


--
-- Name: enigma_kits fkbo6nlfo77ot7be0uqb22hicwb; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.enigma_kits
    ADD CONSTRAINT fkbo6nlfo77ot7be0uqb22hicwb FOREIGN KEY (enigma_id) REFERENCES public.enigma(id);


--
-- Name: usr_roles fkchnh142fcr245xa6c2t8t0fen; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usr_roles
    ADD CONSTRAINT fkchnh142fcr245xa6c2t8t0fen FOREIGN KEY (users_id) REFERENCES public.usr(id);


--
-- Name: kit fkp7xgx0xwitvymo28pk70hrtun; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.kit
    ADD CONSTRAINT fkp7xgx0xwitvymo28pk70hrtun FOREIGN KEY (user_id) REFERENCES public.usr(id);


--
-- Name: kit_enigmas fksp71bjt0kmvgw3pp3m0ia1ocr; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.kit_enigmas
    ADD CONSTRAINT fksp71bjt0kmvgw3pp3m0ia1ocr FOREIGN KEY (enigmas_id) REFERENCES public.enigma(id);


--
-- PostgreSQL database dump complete
--

