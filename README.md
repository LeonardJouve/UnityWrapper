# UnityWrapper
test unity as a library

- add ```unityStreamingAssets=``` in project gradle.properties
- add ```ndk.dir=your_ndk_path``` in local.properties (NDK path can be found in Unity preferences)
- add ```<string name="game_view_content_description">Game view</string>``` in app/res/values/strings.xml
- export your unity project with ```IL2CPP``` as scripting backend, ARMv7 and ARM64 architecture, 'Export Project' and 'Symlink Sources' checked
- import unityLibrary as a module
- add these 2 dependencies in build.gradle (Module :app)
    ```
    implementation(project(':unityLibrary'))
    implementation fileTree(dir: project(':unityLibrary').getProjectDir().toString() + ('\\libs'), include: ['*.jar'])
    ```
- add ```android:exported="true"``` to com.unity3d.player.UnityPlayerActivity in unityLibrary's manifest
- add a FrameLayout with id ```frame_layout``` on layout activity_main
- add these lines at the end of MainActivity.java onCreate method
    ```
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.frame_layout, UnityFragment.class, null)
        .commit();
    ```
- create UnityFragment containing this
    ```
    public class UnityFragment extends Fragment {
        protected UnityPlayer mUnityPlayer;
    
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            mUnityPlayer = new UnityPlayer(getActivity());
            mUnityPlayer.requestFocus();
            mUnityPlayer.windowFocusChanged(true);
            return mUnityPlayer.getView();
        }
    
        @Override
        public void onDestroy() {
            mUnityPlayer.quit();
            super.onDestroy();
        }
    
        @Override
        public void onPause() {
            super.onPause();
            mUnityPlayer.pause();
        }
    
        @Override
        public void onResume() {
            super.onResume();
            mUnityPlayer.resume();
        }
    }
    ```
- ! App does not support x86 architecture (like standard emulators for exemple)
