select active_invocations,
       use_cpu,
       ROUND((percentile_cont(0.01) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p1,
       ROUND((percentile_cont(0.1) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p10,
       ROUND((percentile_cont(0.25) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p25,
       ROUND((percentile_cont(0.5) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p50,
       ROUND((percentile_cont(0.75) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p75,
       ROUND((percentile_cont(0.9) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p90,
       ROUND((percentile_cont(0.99) WITHIN GROUP ( ORDER BY duration ))::numeric, 2) as p99,
       COUNT(*)
from analysis
GROUP BY use_cpu, active_invocations
ORDER BY use_cpu, active_invocations ASC;