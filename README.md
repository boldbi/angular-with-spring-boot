
---

# BoldBI Embedding Angular with Spring Boot Sample

This project demonstrates how to render dashboards from your **Bold BI server** using an **Angular frontend** and a **Spring Boot backend**.

## Dashboard view

![Dashboard View](images/angular-dashboard.png)

---

# Requirements / Prerequisites

* [Node.js](https://nodejs.org/en/)
* [Java JDK 17 or later](https://www.oracle.com/java/technologies/downloads/)
* [Visual Studio Code](https://code.visualstudio.com/download)

> **NOTE:** Node.js versions **18.18 – 20.20** are supported.

---

# Supported browsers

* Google Chrome
* Microsoft Edge
* Mozilla Firefox

---

# Configuration

### Enable Embed Authentication

Ensure **Embed Authentication** is enabled in the **Embed Settings** page.

If it is not enabled, follow the instructions in the official documentation:

[https://help.boldbi.com/site-administration/embed-settings/#get-embed-secret-code?utm_source=github&utm_medium=backlinks](https://help.boldbi.com/site-administration/embed-settings/#get-embed-secret-code?utm_source=github&utm_medium=backlinks)

![Embed Settings](images/enable-embedsecretkey.png)

---

### Download Embed Configuration

Download the **embedConfig.json** file by following this documentation:

[https://help.boldbi.com/site-administration/embed-settings/#get-embed-configuration-file?utm_source=github&utm_medium=backlinks](https://help.boldbi.com/site-administration/embed-settings/#get-embed-configuration-file?utm_source=github&utm_medium=backlinks)

Refer to the images below for guidance.

![Embed Settings Download](images/download-embedsecretkey.png)

![EmbedConfig Properties](images/embedconfig-file.png)

---

### Place the Configuration File

Copy the downloaded **embedConfig.json** file into the required location in the project.

The sample project contains folders showing where the configuration file should be placed.

![EmbedConfig Location](images/embedconfig-location.png)

---

# Running the Sample

# Running the Spring Boot Backend

1. Open the **Spring Boot project folder** in Visual Studio Code.

2. Make sure the following VS Code extensions are installed:

   * **Extension Pack for Java**
   * **Spring Boot Extension Pack**

3. Click the **Spring Boot Dashboard** icon in the Activity Bar.

![Image](images/spring-boot-dashboard.png)

4. Locate your Spring Boot application in the dashboard.

5. Click the **Run** ▶ button next to the application.

The Spring Boot server will start.

The backend API will be available at:

```
http://localhost:8080
```

---

## Angular Frontend

1. Navigate to the **Angular** folder.

```bash
cd Angular
```

2. Install dependencies

```bash
npm install
```

3. Start the Angular development server

```bash
npm start
```

The Angular application will start at:

```
http://localhost:4200
```

Open this URL in your browser to view the embedded dashboard.

---

# Important Notes

* Do **not store passwords or sensitive information** directly in configuration files.
* In production environments, store secrets securely using services like **Azure Key Vault** or other secret management systems.

---

# Online Demo

Live demo:

[https://samples.boldbi.com/embed?utm_source=github&utm_medium=backlinks](https://samples.boldbi.com/embed?utm_source=github&utm_medium=backlinks)

---

# Documentation

Full Bold BI Embedding documentation:

[https://help.boldbi.com/embedded-bi/javascript-based/?utm_source=github&utm_medium=backlinks](https://help.boldbi.com/embedded-bi/javascript-based/?utm_source=github&utm_medium=backlinks)

---