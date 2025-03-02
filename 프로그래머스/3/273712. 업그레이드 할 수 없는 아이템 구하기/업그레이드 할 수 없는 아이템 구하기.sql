-- 코드를 작성해주세요
select I.item_id, I.item_name, I.rarity
from ITEM_INFO I
where I.item_id not in (select PARENT_ITEM_ID from ITEM_TREE where PARENT_ITEM_ID is not null)
order by item_id desc