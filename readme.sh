<h1 align="center">Hi there, I'm <a href="https://www.linkedin.com/in/ivan-ivashchenko/" target="_blank">Ivan</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">This is the repository for the practical and theoretical classes in 
<a href="https://ithillel.ua/" target="_blank">IT Hillel</a></h3>

<div>
    <ul>
        My aim in enrolling for this course is to enhance my comprehension, structure my existing knowledge, and obtain 
feedback.
    </ul>
</div>

echo "# Project Dependencies" > readme.txt
echo "" >> readme.txt
echo "This document provides an overview of the project dependencies listed in the 'pom.xml' file. Please note that this information is subject to change as the project evolves." >> readme.txt
echo "" >> readme.txt
echo "## Dependencies" >> readme.txt

# Extract dependencies from pom.xml and append to readme.txt
mvn -q clean install
mvn dependency:tree | grep "\[INFO\] " | sed 's/\[INFO\] //g' | sed 's/  / /g' | sed 's/ \[.*\]//g' >> readme.txt

echo "" >> readme.txt
echo "## Updating Dependencies" >> readme.txt
echo "" >> readme.txt
echo "To update project dependencies, modify the 'pom.xml' file by changing the version numbers of the respective dependencies. Always refer to the official documentation of each dependency for any breaking changes or new features introduced in newer versions." >> readme.txt
echo "" >> readme.txt
echo "## Notes" >> readme.txt
echo "" >> readme.txt
echo "- Regularly update this document as new dependencies are added or existing ones are removed." >> readme.txt
echo "- Provide brief descriptions and purposes for each dependency to enhance understanding." >> readme.txt
echo "- Ensure that the 'pom.xml' file accurately reflects the project's dependencies." >> readme.txt
