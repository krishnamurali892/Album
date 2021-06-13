This is a demo android appliaction which is developed on MVVM Architectural design pattern by using Kotlin programming language.

By referring this application code we can understand most of the latest android application development concepts like

**MVVM**
 - It is a architectural design pattern, where presentation, business and data layers are loosely coupled.
   So that the application can be easily testable and maintainable(Modifiable)
 
**DataBinding**(no more findViewById's)
 - This library allows us to bind(inject) views into code(XML -> Kotlin) and binding data into views(XML <- Kotlin),
   without having any view's reference explicitly(findViewById's).
 - DataBinding library is required for binding data(java/kotlin) and UI(xml), As in upcoming days Jetpack component 'Compose'
   can be used to draw views dynamically in code itself, So in future DataBinding library may not required.

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
  thread by saving current state of execution, which is called as context switching.
- This feature makes effiecient usage of threads(CPU), which in turn improves system performance. 
  But which cannot be achieved through any traditional multithreading frameworks like Executors, RxJava etc.
- [Coroutine + flow] can easily replace RxJava/RxKotlin

**Retrofit**
- REST-Client based webservices implementation with Retrofit library.
- Its simple to use and removes a lots of efforts for making REST based API calls.
- API calls are aynchromous and it will taken care by library itself.

**Glide**
 - Its a image loading library and images are loaded and stored in cache asynchronously.
 - As compared to other available libraries(Picasso) it can also support playig GIF files too.

**RecyclerView**
 - RecyclerView with filter option
 - Implemented action bar search view for filtering recyclerview contents

**Parcelize**(Annotation based)
 - Its a kotlin programming language plugin meant for Serialization/Deserialization of objects.
 - As we know In Android we will use Parcelable Interface instead Java's Serializable interface,
   to write our own custom logic to serialize an object, But it needs a manual work and adds an extra code.
 - This manual work can be automated and code can be generated on behalf of us at compile time by Parcelize kotlin plugin.
   

