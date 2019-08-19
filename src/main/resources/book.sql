CREATE TABLE public.bookdata
(
    bookid serial PRIMARY KEY,
    status VARCHAR (255),
    bookautor VARCHAR (255),
    bookname VARCHAR (255),
    review VARCHAR (255),
    code bigint
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.bookdata
    OWNER to postgres;


INSERT INTO public.bookdata(
	bookid, status, bookautor, bookname, review, code)
	VALUES (1, 'availible', 'merlin', 'abrakadabra', 'magic', 1);