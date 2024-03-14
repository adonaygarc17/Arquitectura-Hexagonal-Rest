CREATE OR REPLACE FUNCTION GetAllBills()
    RETURNS SETOF facturadb LANGUAGE plpgsql AS '
    BEGIN
        RETURN QUERY
            SELECT facturasubtotal AS subtotal,
                   totaldiscount AS discountAmount,
                   facturatax AS taxes,
                   total AS amountToPay,
                   id AS id
            FROM facturadb;
    END;
';

CREATE OR REPLACE FUNCTION GetAllProducts()
    RETURNS SETOF productdb LANGUAGE plpgsql AS '
    BEGIN
        RETURN QUERY
            SELECT *
            FROM productdb;
    END;
';

create or replace  function saveBill(
    p_subtotal FLOAT,
    p_discountTotal FLOAT,
    p_taxes FLOAT,
    p_total FLOAT,
    p_id VARCHAR
) returns setof facturadb language plpgsql as '
    begin
        insert into facturadb(id, facturasubtotal, totaldiscount, facturatax, total)
        values (p_id, p_subtotal, p_discountTotal, p_taxes, p_total);
    END;';

CREATE OR REPLACE FUNCTION saveProducts(
    p_discountAmount FLOAT,
    p_discountPercentage FLOAT,
    p_finalPrice FLOAT,
    p_category VARCHAR,
    p_brand VARCHAR,
    p_title VARCHAR,
    p_thumbnail VARCHAR,
    p_dbId VARCHAR
) RETURNS setof productdb language plpgsql AS '
    BEGIN
        INSERT INTO productdb(facturadb_id, discounttotal, discountpercentage, finalprice, category, brand, title, thumbnail)
        VALUES (p_dbId, p_discountAmount, p_discountPercentage, p_finalPrice, p_category, p_brand, p_title, p_thumbnail);
    END;';


