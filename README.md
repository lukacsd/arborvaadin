## Arbor-Vaadin

Arbor-Vaadin is an **Arbor.js** Java binding for the **Vaadin 6** framework. It provides the
user with functionality to model a graph as POJOs, and also makes pushing the model from
the server component to the client available.

The integration consists of the following:

* **server** part, where the necessary script dependencies are included in the extended Vaadin
  dispatcher servlet - this will need to be referenced in the web.xml
* **client** component, which is basically a native wrapper, forwarding calls to the arbor.js
  `particleSystem` object

### Arbor.js

Arbor.js is a very nice, interactive, force-layout graph library by the way. See [arborjs.org](http://arborjs.org)

### Building From Source

Pull latest from repo `git pull origin`, then use Maven to build `mvn clean package`. Drop the
war file into a servlet container to see the demo.

### Live Demo

I have deployed the demo on [OpenShift](http://arborvaadin-lukacsd.rhcloud.com/arbor-graph).
Unfortunately however, I don not have resources to maintain it - if it is down, it is down.

### License

Arbor-Vaadin is released under the [MIT license](http://en.wikipedia.org/wiki/MIT_License).
