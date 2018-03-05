from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader


def index(request):
    template = loader.get_template('lucky/index.html')
    html = template.render()
    response = HttpResponse(html)
    return response
