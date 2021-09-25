from locust import task, TaskSet, HttpUser, constant
import os
import urllib3


class PlaybackTest(TaskSet):

    def __init__(self, parent):
        super(PlaybackTest, self).__init__(parent)

    @task
    def session_start_apache(self):
        self.client.post("/session/apache/start", timeout=60)

    # @task
    # def session_start_java(self):
    #     self.client.post("/session/java/start", timeout=60)
    #
    # @task
    # def session_start_spring(self):
    #     self.client.post("/session/spring/start", timeout=60)


class PlaybackUser(HttpUser):
    host = os.getenv('PLAYBACK_HOSTNAME') + ":" + os.getenv('PLAYBACK_PORT')
    tasks = [PlaybackTest]
    wait_time = constant(1)
    urllib3.disable_warnings()
