import requests
from datetime import datetime, timedelta


class ZipkinDataImporter:
    host = None
    port = None

    def __init__(self, host, port):
        self.host = host
        self.port = port

    def import_traces(self, start: datetime, duration: timedelta, limit):
        look_back = duration.total_seconds() * 1000
        end_ts = (start + duration).timestamp() * 1000
        url = "http://%s:%d/api/v2/traces?lookback=%d&endTs=%d&limit=%d" % (
            self.host, self.port, int(look_back), int(end_ts), limit)
        json_response = requests.get(url).json()
        return json_response

    def import_dependencies(self, look_back: int, end_ts: int):
        url = "http://%s:%d/api/v2/dependencies?lookback=%d&endTs=%d" % (
            self.host, self.port, look_back, end_ts)
        json_response = requests.get(url).json()
        return json_response
