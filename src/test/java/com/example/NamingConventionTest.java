package com.example;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = {"com.example"},
        importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class NamingConventionTest {

    @ArchTest
    public static final ArchRule Controller_must_endwith_controller_in_application_layer = classes()
            .that()
            .resideInAnyPackage("com.example.api")
            .should()
            .haveNameMatching(".*Controller")
            .as("Restful API must end with Controller");

    @ArchTest
    public static final ArchRule Dto_must_endwith_request_in_application_layer = classes()
            .that()
            .resideInAnyPackage("com.example.api.request").should().haveNameMatching(".*Request.*")
            .as("DTO class must end with Request or Response");

    @ArchTest
    public static final ArchRule Dto_must_endwith_response_in_application_layer = classes()
            .that()
            .resideInAnyPackage("com.example.api.response").should().haveNameMatching(".*Response.*")
            .as("DTO class must end with Request or Response");


    @ArchTest
    public static final ArchRule ApplicationService_must_endwith_Service_in_application_layer = classes()
            .that()
            .resideInAnyPackage("com.example.application")
            .should()
            .haveNameMatching(".*Service")
            .as("Application service class must end with Service");
    @ArchTest
    public static final ArchRule RepositoryInterface_must_endwith_repository_in_Domain_layer = classes()
            .that()
            .resideInAnyPackage("com.example.domain.repository")
            .should()
            .haveNameMatching(".*Repository")
            .as("Repository interface must end with Repository");

    @ArchTest
    public static final ArchRule Dao_must_endwith_dao_in_infrastructure_layer = classes()
            .that()
            .resideInAnyPackage("com.example.infrastructure.dao")
            .should()
            .haveNameMatching(".*Dao")
            .as("infra dao must end with Dao");

    @ArchTest
    static final ArchRule no_cycles_by_method_calls_between_slices =
            slices().matching("..(api).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

}
