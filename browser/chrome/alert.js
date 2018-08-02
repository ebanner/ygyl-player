// Code for playing all the WebMs on a YGYL thread.

function getThumbnails () {
    return document.querySelectorAll(".fileThumb > img");
}

function getWebms () {
    return document.querySelectorAll("video");
}

function playWebm (i) {
    if (i >= getThumbnails().length) {
        return; // no more thumbnails
    }

    // Keep clicking on thumbnails until you spawm a webm
    // (a thumbnail might be an image or a GIF).
    thumbs = getThumbnails();
    while (true) {
        thumb = thumbs[i];
        numWebms = getWebms().length;
        thumb.click();
        if (getWebms().length > numWebms) {
            break;
        } else {
            i++;
        }
    }

    // Get the newest webm.
    webms = getWebms();
    webm = webms[webms.length - 1];

    // Unmute and unloop the webm.
    webm.muted = false;
    webm.loop = false;

    // Do it all over again when this one ends.
    webm.onended = function () {
        playWebm(i+1);
    };
}

playWebm(0);