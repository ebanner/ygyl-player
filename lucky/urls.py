from django.urls import path

from . import views

app_name = 'lucky'
urlpatterns = [
    path('webm', views.webm, name='webm'),
]
