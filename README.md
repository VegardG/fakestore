The UI is simple with big buttons that are mostly in the same area of the screen that makes it easy and convenient to use with no unnecessary menus or overwhelming options. 
I used Retrofit to handle network operations as this simplifies using RESTful API. Room was used for the local database making the database usage more efficient and less error prone. A RecyclerView was used multiple times to display a list of items which was required on the main product page, the order history and in the cart. For the image loading I first used Picasso but after some dependency conflicts and realizing Glide was a better option with less memory used and better integration with android components. 

The app uses a modular design that has different modules that are responsible for their own tasks. This method makes it easier to scale, maintain and reuse the different modules. For example, the network and database are separate making changes to one doesn’t directly impact the other. The ProductAdapter is used multiple places to render product lists for reusability. 

I used the Model-View-Controller Architecture that separates the internal representation of the information from the way it’s presented to the user. The Model in the app is responsible for fetching, storing, and manipulating data. The View is rendering the user interface and presenting data to the user, for example, the xml files define the structure of the UI and the Activity files control how the data is shown. Lastly the controller listens to the users input reported by the view and executes the appropriate reaction like updating the Model. 

A problem the app has at the moment is the ‘remove’ button in the shopping cart. It works as intended and removes the item from the cart, but not visually. You can still see the product after you remove it unless you go out of the cart and back it. I’m certain it’s a problem with how the view gets updated when clicking the button, but I don’t want to spend all my time on this as time is running out with other exams. 

Reflection
What I would do differently a second time is more planning. Although I feel like the structure and overview of the project got pretty good in the end, sometimes I was a bit all over the place with file locations, or not knowing what to integrate next. Also I would do more research on things like Glide versus Picasso so I don’t have to suddenly change it after realizing one would be much better than the other. 
The thing I spent the most time on was getting the orders to show up in the order history. I was having some trouble on how to handle the data. Also trying to get the remove button to work correctly as it sadly doesn’t at the moment.
I know I said that I would do more planning on this the second time around, but I still feel the most satisfying thing is the structure of the project. A lot of different modules and parts working together to make the app functional is pretty cool 😊
