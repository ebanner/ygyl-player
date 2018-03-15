from django.test import TestCase
from django.urls import reverse

import lucky.views


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

class SupportFunctionTests(TestCase):
    def test_get_ygyl_id_not_none(self):
        out = lucky.views.get_ygyl_id()
        self.assertIsNotNone(out)
