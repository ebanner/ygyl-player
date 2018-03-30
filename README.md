# ygyl-player

Web app for playing a random webm from the latest [*You Groove You Lose* (YGYL)](https://www.urbandictionary.com/define.php?term=ygyl) thread

Currently hosted at https://sleepy-meadow-97796.herokuapp.com/static/index.html

## About

This app uses the [4chan API](https://github.com/4chan/4chan-API) to identify the latest [*You Groove You Lose* (YGYL)](https://www.urbandictionary.com/define.php?term=ygyl) thread then plays a random webm from it. My use case is to launch and leave it open in a tab. After the current webm finishes another one will be fetched and played.

## Browser Differences

If you want to leave the app open in a tab and autoplay webms ad infinitum then use firefox. Chrome will not autoplay webms.

It does not work on Safari last I checked.

## Credits

- Built with [Django](https://www.djangoproject.com/) and [reagent](https://reagent-project.github.io/).
- Developed with [pynt](https://github.com/ebanner/pynt).
- Deployed with [Heroku](https://www.heroku.com).

## Special Thanks

Thanks to all the 4chan users who upload these amazing webms!
