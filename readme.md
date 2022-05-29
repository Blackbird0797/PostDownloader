<h1 style="color: #03A9F4">
  Post Downloader
</h1>

<h4 align="">Kotlin app for downloading posts from API and saving them as JSON files.</h4>

<p>
  <a  style="color: #03A9F4" href="#how-to-use">How To Use</a> •
  <a  style="color: #03A9F4" href="#run">Run application</a> •
  <a  style="color: #03A9F4" href="#credits">Credits</a> 
</p>

<div id="how-to-use">

## How To Use

Application requires JDK 11 to run. Make sure all environment variables including **JAVA_HOME** are set.

Before running the app you need to create `config.json` file with **two** required entries:

- ApiUri - API base url
- SaveFilePath - local path where files will be stored

```json
{
  "ApiUri": "https://jsonplaceholder.typicode.com",
  "SaveFilePath": "C:\\example\\file\\path"
}
```

> **NOTE** Created file needs to be saved in project root folder
</div>

<div id="run">

## Run application

To run the app go to root project folder and type `gradlew run` or `.\gradlew run`

</div>

<div id="credits">

## Credits

Application uses multiple external libraries including:

- [retrofit](https://square.github.io/retrofit/)
- [kotlinx-coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [kotlin-logging](https://github.com/MicroUtils/kotlin-logging)
- [mockk](https://mockk.io/)
- [junit5](https://junit.org/junit5/)

</div>