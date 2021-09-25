from datetime import datetime, timedelta

from ZipkinDataImporter import ZipkinDataImporter
import psycopg2

conn = psycopg2.connect(database="admin", user="admin", password="admin", host="192.168.1.28", port="10000")
cursor = conn.cursor()
host = "192.168.1.28"

start = datetime.strptime('2021-07-31 00:00:00', '%Y-%m-%d %H:%M:%S')
duration = timedelta(hours=20)

zipkin_importer = ZipkinDataImporter(host=host, port=9411)
zipkin_traces = zipkin_importer.import_traces(start=start, duration=duration, limit=1000)

results = []
for zipkin_trace in zipkin_traces:
    for zipkin_span in zipkin_trace:
        if zipkin_span['name'] == 'create-device':
            tags = zipkin_span['tags']
            subject = tags.get('class', 'None') + "." + tags.get('method', 'None')
            active_invocations = tags['active_invocations']
            use_cpu = tags['use_cpu']
            duration = zipkin_span['duration'] / 1000
            id = zipkin_span['id']
            cursor.execute("""
                INSERT INTO analysis (id, active_invocations, duration, use_cpu, subject)
                VALUES (%s, %s, %s, %s, %s)
                ON CONFLICT (id, subject) 
                DO NOTHING;
                """, (id, active_invocations, duration, use_cpu, subject))
            print(active_invocations + ", " + str(duration) + "ms," + use_cpu)
conn.commit()
cursor.close()
conn.close()
