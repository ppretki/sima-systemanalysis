from locust import task, SequentialTaskSet, HttpUser, constant, LoadTestShape
import os
import urllib3


class PlaybackTest(SequentialTaskSet):

    def __init__(self, parent):
        super(PlaybackTest, self).__init__(parent)

    @task
    def session_start(self):
        self.client.post("/session/start", timeout=60)


class PlaybackUser(HttpUser):
    host = os.getenv('PLAYBACK_HOSTNAME') + ":" + os.getenv('PLAYBACK_PORT')
    tasks = [PlaybackTest]
    wait_time = constant(1000)
    urllib3.disable_warnings()
