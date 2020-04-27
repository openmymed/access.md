const PRECACHE = 'precache-5';


//SPACE LEFT HERE SO JENKINS CAN REPLACE THE NAME OF THE CACHE  WITH THE
//BUILD NUMBER. DO NOT TOUCH THIS.
const RUNTIME = 'runtime';

const PRECACHE_URLS = [
    'index.html',
    'app.js'
];
const channel = new BroadcastChannel('sw-messages');

self.addEventListener('install', event => {
    event.waitUntil(
            caches.open(PRECACHE)
            .then(cache => cache.addAll(PRECACHE_URLS))
            .then(self.skipWaiting())
            );
});

// The activate handler takes care of cleaning up old caches.
self.addEventListener('activate', event => {

    const currentCaches = [PRECACHE, RUNTIME];
    event.waitUntil(
            caches.keys().then(cacheNames => {
        return cacheNames.filter(cacheName => !currentCaches.includes(cacheName));
    }).then(cachesToDelete => {
        return Promise.all(cachesToDelete.map(cacheToDelete => {
            return caches.delete(cacheToDelete);
        }));
    }).then(() => self.clients.claim())
            );

});

self.addEventListener('fetch', event => {
    if (event.request.url.startsWith(self.location.origin)
            && event.request.method === "GET"
            && (event.request.url.match(/.*\.(js|html|css|png|jpeg)/g) !== null)) {
        event.respondWith(
                caches.match(event.request).then(cachedResponse => {
            if (cachedResponse && cachedResponse.ok) {
                return cachedResponse;
            }
            return caches.open(RUNTIME).then(cache => {
                return fetch(event.request).then(response => {
                    return cache.put(event.request, response.clone()).then(() => {
                        return response;
                    });
                });
            });
        })
                );
    }
});

self.addEventListener('notificationclick', (e) => {
    // Close the notification popout
    e.notification.close();
    clients.matchAll({type: 'window'}).then(clients => {
        if (clients.length > 0) {
            if (e.notification.data) {
                channel.postMessage({type: "REDIRECT", view: e.notification.data.view, params: e.notification.data.params});
            }
        } else {
            return clients.openWindow("./")
        }
    })
});

const pollNotifications = () => {
    fetch("/notification", {
        method: "GET",
        credentials: "same-origin",
        headers: {
            "Content-Type": "application/json",
        }
    }).then((res) => {
        if (res.ok) {
            return res.json();
        } else {
            throw new Error("Failed to fetch");
        }
    }).then((json) => {
        channel.postMessage({type: "NOTIFICATIONS", notifications: json});
    }).catch((e) => {/*do nothing*/
    });
    self.setTimeout(() => {
        pollNotifications();
    }, 30000);
}

pollNotifications();