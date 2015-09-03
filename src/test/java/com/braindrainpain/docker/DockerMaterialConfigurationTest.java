/*
The MIT License (MIT)

Copyright (c) 2014 Jan De Cooman

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.braindrainpain.docker;

import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import junit.framework.TestCase;

/**
 * @author Manuel Kasiske
 */
public class DockerMaterialConfigurationTest extends TestCase {


    public void testValidationWithDefaultRepositoryConfigurationShouldFail() {
        DockerMaterialConfiguration dockerMaterialConfiguration = new DockerMaterialConfiguration();
        RepositoryConfiguration repositoryConfiguration = dockerMaterialConfiguration.getRepositoryConfiguration();

        assertFalse(dockerMaterialConfiguration.isRepositoryConfigurationValid(repositoryConfiguration).isSuccessful());
    }

    public void testValidationWithDefaultPackageConfigurationShouldFail() {
        DockerMaterialConfiguration dockerMaterialConfiguration = new DockerMaterialConfiguration();
        RepositoryConfiguration repositoryConfiguration = dockerMaterialConfiguration.getRepositoryConfiguration();
        PackageConfiguration packageConfiguration = dockerMaterialConfiguration.getPackageConfiguration();

        assertFalse(dockerMaterialConfiguration.isPackageConfigurationValid(packageConfiguration, repositoryConfiguration).isSuccessful());
    }


    public void testValidationOfRepositoryConfiguration() {
        DockerMaterialConfiguration dockerMaterialConfiguration = new DockerMaterialConfiguration();
        RepositoryConfiguration repositoryConfiguration = dockerMaterialConfiguration.getRepositoryConfiguration();
        repositoryConfiguration.get(Constants.REGISTRY).withDefault("http://www.google.de");

        assertTrue(dockerMaterialConfiguration.isRepositoryConfigurationValid(repositoryConfiguration).isSuccessful());
    }


    public void testValidationOfPackageConfiguration() {
        DockerMaterialConfiguration dockerMaterialConfiguration = new DockerMaterialConfiguration();
        RepositoryConfiguration repositoryConfiguration = dockerMaterialConfiguration.getRepositoryConfiguration();
        repositoryConfiguration.get(Constants.REGISTRY).withDefault("http://www.google.de");
        PackageConfiguration packageConfiguration = dockerMaterialConfiguration.getPackageConfiguration();
        packageConfiguration.get(Constants.REPOSITORY).withDefault("abc");

        assertTrue(dockerMaterialConfiguration.isPackageConfigurationValid(packageConfiguration, repositoryConfiguration).isSuccessful());
    }


}
