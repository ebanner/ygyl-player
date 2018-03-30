# ygyl-player

Web app for pulling a random webm from the latest [*You Groove You Lose* (YGYL)](https://www.urbandictionary.com/define.php?term=ygyl) thread

Currently hosted at https://sleepy-meadow-97796.herokuapp.com/static/index.html

## About

This app uses the [4chan API](https://github.com/4chan/4chan-API) to identify the latest [*You Groove You Lose* (YGYL)](https://www.urbandictionary.com/define.php?term=ygyl) thread then plays a random webm from it. My use case is to launch and leave it open in a tab. After the current webm finishes another one will be fetched and played.

## Browser Differences

If you want to leave the app open in a tab and autoplay webms ad infinitum then use firefox. Chrome will not autoplay each webm. 

## Credits

- Built with [Django](https://www.djangoproject.com/) and [ClojureScript](https://www.google.com/search?q=clojurescript&oq=clojurescript&aqs=chrome..69i57j69i60l3j0j69i65.2318j0j4&sourceid=chrome&ie=UTF-8).
- Developed with [pynt](https://github.com/ebanner/pynt).
- Deployed with [Heroku](https://www.heroku.com).

## Special Thanks

Thanks to all the 4chan users who upload these amazing webms!
