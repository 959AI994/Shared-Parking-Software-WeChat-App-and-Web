# Shared Parking Software (WeChat App & Web)

This project includes both a WeChat mini-program and a web application designed for shared parking solutions. The software utilizes modern technologies such as Vue.js, Element UI, axios, and integrates map functionality for store locations. Below are instructions to get started with both the WeChat app and the web version of the project.

## Build Setup

### Clone the repository

```bash
# Clone the project
git clone https://github.com/959AI994/Shared-Parking-Software-WeChat-App-and-Web.git

# Enter the project directory
cd Shared-Parking-Software-WeChat-App-and-Web

# Install dependencies
npm install

# Start development mode
npm run dev
```

After running these commands, the application will automatically open in the browser at: `http://localhost:9528`.

---

## Build for Different Environments

You can build the project for different environments by running the following commands:

### Build for Test Environment

```bash
npm run build:stage
```

### Build for Production Environment

```bash
npm run build:prod
```

---

## Advanced Features

### Preview the Release Environment

```bash
# Preview the release environment
npm run preview
```

### Preview with Static Resource Analysis

```bash
# Preview the release environment and analyze static resources
npm run preview -- --report
```

### Code Format Check

```bash
# Check code formatting
npm run lint
```

### Code Format Check with Auto-fix

```bash
# Check and automatically fix code formatting
npm run lint -- --fix
```

---

## Browsers Support

This project supports modern browsers and Internet Explorer 10+.

| ![IE / Edge](https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png) | ![Firefox](https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png) | ![Chrome](https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png) | ![Safari](https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png) |
| -------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| IE10, IE11, Edge                                                                                   | last 2 versions                                                                                        | last 2 versions                                                                                     | last 2 versions                                                                                     |

---

## WeChat Mini-Program Permissions

In the `manifest.json` file, permission for location access needs to be authorized for the WeChat mini-program:

```json
"permission": {
  "scope.userLocation": {
    "desc": "Your location information will be used for map functionality."
  }
}
```

Make sure to include this configuration to enable location access properly. Simply configuring in `pages.json` won't work.

---

## Example Usage (WeChat Mini-Program)

Here is an example of how you can integrate the store location and markers on the map in the WeChat Mini Program:

```html
<template>
    <view class="content">
        <hchPosition :storeData="storeData" :markers="markers"></hchPosition>
    </view>
</template>

<script>
import hchPosition from "../../wxcomponents/hch-position/hch-position"

export default {
    data() {
        return {
            storeData: [
                {
                    id: 1,
                    name: 'Store 1',
                    address: 'Address 1',
                    tel: '12345678900',
                },
                {
                    id: 2,
                    name: 'Store 2',
                    address: 'Address 2',
                    tel: '12345678900',
                },
            ],
            markers: [
                {
                    id: 1,
                    latitude: 24.4483294023427,
                    longitude: 118.08479034393311,
                    iconPath: '../../static/store.png',
                    callout: {
                        content: "Store 1",
                        borderRadius: 10,
                        padding: 10,
                        display: "ALWAYS",
                    }
                },
                {
                    id: 2,
                    latitude: 24.45580,
                    longitude: 118.12266,
                    iconPath: '../../static/store.png',
                    callout: {
                        content: "Store 2",
                        borderRadius: 10,
                        padding: 10,
                        display: "ALWAYS",
                    }
                },
            ],
        }
    },
    components: {
        hchPosition,
    },
    methods: {},
    onShow() {},
}
</script>
```

This code integrates a custom component, `hchPosition`, which displays store data and markers on a map, complete with location and contact information.

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---

This `README` provides all the necessary steps to get started with both the web version and WeChat mini-program of your parking solution. Let me know if you need any further modifications or additional information!
