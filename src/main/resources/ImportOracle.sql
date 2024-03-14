create Or replace PROCEDURE GetAllBills(p_cursor OUT SYS_REFCURSOR) AS
BEGIN
OPEN p_cursor FOR
SELECT FACTURASUBTOTAL AS subtotal,
       TOTALDISCOUNT AS discountAmount,
       FACTURATAX AS taxes,
       TOTAL AS amountToPay,
       ID AS id
FROM FACTURADB;
END GetAllBills;

create Or replace PROCEDURE GetAllProducts(p_cursor OUT SYS_REFCURSOR) AS
BEGIN
OPEN p_cursor FOR
SELECT FACTURADB_ID as dbBillId,
       TITLE AS subtotal,
       CATEGORY AS category,
       BRAND AS brand,
       THUMBNAIL AS urlThumbnail,
       DISCOUNTPERCENTAGE AS discountPércentage,
       DISCOUNTTOTAL AS discountAmount,
       FINALPRICE as  amountToPay

FROM PRODUCTDB;
END GetAllProducts;


create procedure saveBill(
    p_subtotal in float,
    p_discountTotal in float,
    p_taxes in float,
    p_total in float,
    p_id in varchar2
) AS
begin
insert into FACTURADB( ID , FACTURASUBTOTAL, TOTALDISCOUNT, FACTURATAX, TOTAL)
values ( p_id,p_subtotal, p_discountTotal, p_taxes, p_total);
COMMIT;
end;

create procedure saveProducts(
    p_discountAmount in float,
    p_discountPercentage in float,
    p_finalPrice in float,
    p_category in varchar2,
    p_brand in varchar2,
    p_title in varchar2,
    p_thumbnail in varchar2,
    p_dbId in varchar2
) AS
begin
insert into PRODUCTDB(FACTURADB_ID,DISCOUNTTOTAL, DISCOUNTPERCENTAGE, FINALPRICE, CATEGORY, BRAND, TITLE, THUMBNAIL)
values (p_dbId,p_discountAmount, p_discountPercentage, p_finalPrice, p_category, p_brand, p_title, p_thumbnail);
COMMIT;
end;
