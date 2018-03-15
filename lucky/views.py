import requests
from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader


def index(request):
    """Return a hard-coded webm

    Args:
        request (django.core.handlers.wsgi.WSGIRequest): the request object

    """
    template = loader.get_template('lucky/index.html')
    html = template.render()
    return HttpResponse(html)

def get_ygyl_id():
    """Fetch a YGYL thread ID from the 4chan API"""
    pages = requests.get('https://a.4cdn.org/gif/catalog.json').json()
    for page in pages:
        for thread in page['threads']:
            if 'ygyl' in thread.get('sub', 'ylyl').lower():
                return thread['no']

def loop():
    pass
