with recursive time as (
    select 0 as n
    union all
    select n+1
    from time
    where n<23
)

select t.n as hour, count(a.animal_id)
from time t left join ANIMAL_OUTS a on hour(a.datetime) = t.n
group by hour
order by hour