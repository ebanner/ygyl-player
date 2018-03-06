from django.urls import path

from . import views

app_name = 'lucky'
urlpatterns = [
    path('', views.index, name='index'),
]
