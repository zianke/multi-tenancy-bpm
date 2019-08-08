This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:


### `yarn install`
Install all dependencies for the project.

### `yarn build`
Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

### `yarn start`
Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

## Routers
### `/`
Log in webpage.<br>
Firstly, user should select his role: customer or staff. As a customer, the user can log in by his username and password. As a staff, his company and group are also required while logging in.

For demo, we use `admin` as customer to submit orders and track all submitted orders. Staffs for testing include `tubatu_customer-service_1`, `tubatu_customer-service_2`, `tubatu_engineer_1`, `tubatu_engineer_2`, `skshu_customer-service_1`, `skshu_customer-service_2`, `skshu_engineer_1`, `skshu_engineer_2`. Passwords are identical to the usernames.

### `/customer`
A webpage for customer. Two options could be selected in the navigation bar.

- **Submit An Order**: Begin a new request and submit the order to assigned service provider.

- **My Orders**: Obtain all orders the customer has submitted and their details. For now it only works for `admin`.

### `/staff`
A webpage for staff. Two options could be selected in the navigation bar.

- **Pending Orders**: Show all orders could be accepted by the certain user. User can accept the task in table.

- **Processing Orders**: Show all orders still in processing. Customer service could add comments to the order.

