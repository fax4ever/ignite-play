SELECT *
FROM TABLE_1S as t1
JOIN TABLE_2S as t2
   ON t1.ID = t2.TABLE_1_ID
JOIN TABLE_3S as t3
   ON t2.ID = t3.TABLE_2_ID
JOIN TABLE_4S as t4
   ON t3.ID = t4.TABLE_3_ID
-- it blocks with this other join
-- JOIN TABLE_5S as t5
--   ON t4.ID = t5.TABLE_4_ID
WHERE t1.ID = ?