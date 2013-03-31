Android-Shapeways
=================

<p>Example app for invoking the <a href="http://developers.shapeways.com/getting-started?li=dh_gs">Shapeways API</a> from an Android app.</p>

<p>To run the App: 
<ul>
<li>Create an account on <a href="http://shapeways.com">Shapeways</a>.</li>
<li><a href="http://developers.shapeways.com/manage-apps">Register</a> your app with Shapeways.</li>
<li>Import the code into the <a href="http://eclipse.org">Eclipse</a> IDE.</li>
<li>Set the consumer key and secret in com.entertailion.android.shapeways.ShapewaysApplication</li>
<li>Run the app.</li>
<li>The first time you will be prompted to use your Shapeways account login to authorize access.</li>
<li>Press the "Invoke API" button. The API calls are logged. Change the API calls in com.entertailion.android.shapeways.MainActivity.onCreate()</li>
</ul>
</p>

<p>The following is working: 
<ul>
<li>Retrieving the OAuth request and access tokens.</li>
<li>Get user authorization using a web view.</li>
<li>Getting the API and rate limiting information ("/api/v1/")</li>
<li>Getting the list of printers ("/printers/v1")</li>
<li>Getting the list of materials ("/materials/v1")</li>
<li>Getting the list of models ("/models/v1/") [once you have uploaded at least one model into your account]</li>
</ul>
</p>

<p>Limitations: 
<ul>
<li>POST API request isn't working yet.</li>
<li>Not all of the API's have been tested.</li>
</ul>
</p>

