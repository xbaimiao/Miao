package com.xbaimiao.miao;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Miao extends JavaPlugin {

    public static URLClassLoader classloader;
    public static Miao instance;
    private static Method addUrl;
    static {

        classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        try {
            addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrl.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    boolean loadKotlin = loadKotlin();

    public static void addURL(File file) {
        try {
            addUrl.invoke(classloader, file.toURI().toURL());
        } catch (IllegalAccessException | InvocationTargetException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onEnable() {
        if (loadKotlin){
            getLogger().info("Kotlin load success!");
        }
        instance = this;
    }

    @SuppressWarnings("all")
    private boolean loadKotlin() {
        File file = new File(getDataFolder().getPath() + File.separator + "libs" + File.separator + "kotlin.jar");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            if (!file.exists()) {
                OutputStream outputStream = new FileOutputStream(file);
                JarFile plugin = new JarFile(getPluginFileName());
                InputStream inputStream = plugin.getInputStream(plugin.getJarEntry("Kotlin.jar"));
                int len;
                byte[] buff = new byte[1024];
                while ((len = inputStream.read(buff)) > 0) {
                    outputStream.write(buff, 0, len);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
            addURL(file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Nullable
    private String getPluginFileName() {
        ArrayList<JarFile> list = new ArrayList<>();
        File plugins = new File(System.getProperty("user.dir") + File.separator + "plugins");
        if (plugins.listFiles() != null) {
            for (File file : Objects.requireNonNull(plugins.listFiles())) {
                if (!file.isDirectory()) {
                    if (file.getName().endsWith(".jar")) {
                        try {
                            list.add(new JarFile(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            for (JarFile jarFile : list) {
                ZipEntry pluginYml = jarFile.getEntry("plugin.yml");
                if (pluginYml == null) {
                    continue;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(jarFile.getInputStream(pluginYml), StandardCharsets.UTF_8));
                String s;
                while (true) {
                    if (((s = br.readLine()) != null)) {
                        s = s.replace(" ", "");
                        if (s.startsWith("name")) {
                            if (s.split(":")[1].equalsIgnoreCase("Miao")) {
                                return jarFile.getName();
                            }
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
