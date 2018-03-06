from django.test import TestCase
from django.urls import reverse


# Create your tests here.

class LuckyIndexViewTests(TestCase):
    def test_webm(self):
        url = reverse('lucky:index')
        response = self.client.get(url)
        html = response.content.decode()
        self.assertIn('webm', html)

    def test_video(self):
        url = reverse('lucky:index')
        response = self.client.get(url)
        html = response.content.decode()
        self.assertIn('video', html)
