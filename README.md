This is a simple android appliaction which is developed on MVVM Architectural design pattern by using Kotlin programming language.

By referring this application we can understand most of the modern application concepts like

**MVVM**
 - Its a architectural design pattern, where presentation, business and data layers are loosely coupled
   So that the application can be easily testable and maintainable(Modifiable)
 
**DataBinding**(no more findviewById's)
 - This library allows us to bind(injecting) views into code(one way) and binding data into views(two way),
   without having view's reference explicitly(by findViewById's).
 - Even DataBinding will also replaced with Jetpack compose in future.(As no more XML for creating UI)

**Dagger Hilt**(Annotation based)
 - Its a compile time Android based Dependency Injection framework build on top of dagger2 framework.
 - If we want to make our code loosely coupled then we need to go with Manual Dependency Injection,
   but it adds a lot of extra code and time to develop. 
 - This Manual work can be automated with the help of DI frameworks and here Hilt is one among them, 
   which removes lots of boiler plate code as compared to Manual DI and Dagger2.

**Kotlin coroutines**
- For making asynchronous calls AsyncTask's are no more suported and they can be replaced with
  kotlin coroutines
- Coroutines are more power full and add's very less code as compared to all
  traditional approaches like asynctask, RXJava etc.
  **Why they are powerfull?**
- Because a coroutine can pause on one thread and restart on same/different
  thread by saving current state which is called as context switching.
- This feature makes effiecient usage of threads(CPU), which in turn improves system performance. But which cannot be achieved through
  any traditional multithreading frameworks like Executors, RxJava etc.
  
  Coroutine + flow can easily replace RxJava.

**Retrofit**
- REST-Client based webservices implementation with Retrofit library.
  Its simple use and removes a lot of efforts for making REST based API calls.
  API calls are aynchromous and it will taken care by library itself.

**Glide**
 - Its a image loading library and images are loaded and stored in cache asynchronously.
   As compared to other available libraries(Picasso) it can support playig GIF files too.

**RecyclerView**
 - RecyclerView with filter option
