# Wellcome to the SpaceXMindera by Franklin
Simple app which shows a couple information about SpaceX company and its launches

### Archtecture
I've Decided to use MVP just because it's a simple app and theres no reason to make it big and complex.

### Instructions to build
We have 2 main schemas.
* **debug** - It is responsible to set the BuildConfig with debug/develop config (ie. Base URLs)
* **release** - As debug variant, we can set the application to release, using proguard and other optimizations

### Features
* You can see updated information about SpaceX company and a list of launches to space. After click on it, you can check more information about it via Youtube, papers or wikipedia
* Internet Connection verification - An error will be shown

### Important:
* **Retrofit2** - just because I have to request to a server API
* **Picasso** - I like how picasso cache the known pictures (but we can use glide in the same case)
* **Moshi** - handle json response
* **GSon** - handle json response
* **AndroidX** - Migrated to AndroidX
* There's no **Android RX** because it's a simple application
* **Android Lifecycle** - binds, remove smelly code, livedata
* **Activity + Fragment** - easy way to separate the lifecycle and memory optimization
* **RecyclerView** - With viewholder and picasso implementation onto its adapter
* **JUnit** for unit tests

### TESTS
* When you run tests, please, turn animations of on your cellphone ( [AnimationsOFF-DEVICE](https://stackoverflow.com/a/44005848) )

### Next Steps
* Proguard config
* buildTypes tuning
* test coverage
* Check ssl/https certificates when request to Unsplash server (avoiding Man in the middle attacks)
