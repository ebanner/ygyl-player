import urllib

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
    download_webm()
    return HttpResponse(html)

def get_ygyl_id():
    """Fetch a YGYL thread ID from the 4chan API

    Returns:
        `thread_id` where http://boards.4chan.org/gif/thread/{thread_id} is a
        YGYL thread

    """
    pages = requests.get('https://a.4cdn.org/gif/catalog.json').json()
    for page in pages:
        for thread in page['threads']:
            if 'ygyl' in thread.get('sub', 'ylyl').lower():
                return thread['no']

def get_ygyl_thread_posts():
    """Get the posts from a YGYL thread"""
    ygyl_id = get_ygyl_id()
    thread = requests.get(f'https://a.4cdn.org/gif/thread/{ygyl_id}.json').json()
    return thread['posts']

def download_webm():
    posts = get_ygyl_thread_posts()
    post = posts[0]
    tim = post['tim']
    webm_url = f'http://i.4cdn.org/gif/{tim}.webm'
    urllib.request.urlretrieve(webm_url, filename='lucky/static/lucky/example.webm')

def loop():
    pass
