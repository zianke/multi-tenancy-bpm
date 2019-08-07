This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:


### `yarn install`
Install all dependencies for the project.

###`yarn build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

### `yarn start`
Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

##Routers
###`/`
Log in webpage.<br>
Firstly, user should select his role: customer or staff. As a customer, the user can log in by his username and password. As a staff, his company and group are also required while logging in.

###`/customer`
A webpage for customer. Two options could be selected in the navigation bar.<br>
**Submit An Order**: Begin a new request and submit the order to assigned service provider.<br>
**My Orders**: Obtain all orders the customer has submitted and their details.

###`/staff`
A webpage for staff. Two options could be selected in the navigation bar.<br>
**Pending Orders**: Show all orders could be accepted by the certain user. User can accept the task in table.<br>
**Processing Orders**: Show all orders still in processing. Customer service could modify add comments to the order.

###`/admin`
A webpage for admin. Two options could be selected in the navigation bar.<br>
**Create Tenant**: Add a new tenant (company) on the platform.<br>
**Create User**: Add a new user, either customer or staff.
