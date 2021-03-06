This is a demo android appliaction which is developed on MVVM Architectural design pattern by using Kotlin programming language.

By referring this application code we can understand most of the latest android application development concepts like

**MVVM**
 - It is an architectural design pattern, where presentation, business and data layers are loosely coupled.
   So that the application can be easily testable and maintainable(Modifiable)
   
   mvvm.png![mvvm](https://user-images.githubusercontent.com/6782604/124628653-50661b00-de9e-11eb-906e-218d09dfd82b.png)
 
**DataBinding**(no more findViewById's)
 - This library allows us to bind(inject) views into code(XML -> Kotlin) and binding data into views(XML <- Kotlin),
   without having any view's reference explicitly(findViewById's).
 - DataBinding library is required for binding data(java/kotlin) and UI(xml), As in upcoming days Jetpack component 'Compose'
   can be used to draw views dynamically in code itself, So in future DataBinding library may not be required.

**Dagger Hilt**(Annotation based)
 - It's a compile time Android based Dependency Injection framework built on top of dagger2 framework.
 - If we want to make our code loosely coupled then we need to go with Manual Dependency Injection,
   but it adds a lot of extra code and time to develop. 
 - This Manual work can be automated with the help of DI frameworks and here Hilt is one among them, 
   which removes lots of boiler plate code as compared to Manual DI and Dagger2.

**Kotlin coroutines**
- For making asynchronous calls AsyncTask's are no more supported and they can be replaced with
  kotlin coroutines
- Coroutines are more powerful and add very less code as compared to all
  traditional approaches like asynctask, RXJava etc.
  **Why they are powerful?**
- Because a coroutine can pause on one thread and restart on the same/different
  thread by saving the current state of execution, which is called context switching.
- This feature makes efficient usage of threads(CPU), which in turn improves system performance. 
  But this cannot be achievable through any traditional multithreading frameworks like Executors, RxJava etc.
- [Coroutine + flow] can easily replace RxJava/RxKotlin

**Retrofit**
- REST-Client based web services implementation with Retrofit library.
- It's simple to use and removes a lot of effort for making REST based API calls.
- API calls are asynchronous and it will be taken care of by the library itself.

**Glide**
- It's an image loading library and images are loaded and stored in cache asynchronously.
- As compared to other available libraries(Picasso) it can also support playing GIF files too.

**RecyclerView**
 - RecyclerView with filter option
 - Implemented action bar search view for filtering recyclerview contents

**Parcelize**(Annotation based)
 - It's a kotlin programming language plugin, meant for Serialization/Deserialization of objects.
 - As we know In Android we will use Parcelable Interface instead Java's Serializable interface,
   to write our own custom logic to serialize an object, But it needs manual work and adds an extra code.
 - This manual work can be automated and code can be generated on behalf of us at compile time by the Parcelize kotlin plugin.
   

